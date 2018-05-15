---
layout: docs
title: Apache Beam
---

Starting from version 0.3.0, Scio moved from Google Cloud [Dataflow Java SDK](https://github.com/GoogleCloudPlatform/DataflowJavaSDK) to [Apache Beam](https://beam.apache.org/) as its core dependencies and introduced a few breaking changes.

- Dataflow Java SDK 1.x.x uses `com.google.cloud.dataflow.sdk` namespace.
- Apache Beam uses `org.apache.beam.sdk` namespace.
- Dataflow Java SDK 2.x.x is also based on Apache Beam 2.x.x and uses `org.apache.beam.sdk`.

Scio 0.3.x depends on Beam 0.6.0 (last pre-stable release) and Scio 0.4.x depends on Beam 2.0.0 (first stable release). Breaking changes in these releases are documented below.

## Version matrices

Early Scio releases depend on Google Cloud [Dataflow Java SDK](https://github.com/GoogleCloudPlatform/DataflowJavaSDK) while later ones depend on [Apache Beam](https://github.com/apache/beam). Check out the [[Apache Beam]] page for migration guides.

| **Scio** | **SDK Dependency** | **Description**     |
|:--------:|:------------------:|:--------------------|
| 0.5.x    | Apache Beam 2.x.x  | Better type-safe Avro and BigQuery IO |
| 0.3.x    | Apache Beam 0.6.0  | Pre-release Beam    |
| 0.2.x    | Dataflow Java SDK  | SQL-2011 support    |
| 0.1.x    | Dataflow Java SDK  | First releases      |

| **Scio Version** | **Beam Version** |
|:----------------:|:----------------:|
| 0.5.1+           | 2.4.0            |
| 0.5.0            | 2.2.0            |
| 0.4.6+           | 2.2.0            |
| 0.4.1+           | 2.1.0            |
| 0.4.0            | 2.0.0            |
| 0.3.0+           | 0.6.0            |

## Breaking changes since Scio 0.5.0

- `BigQueryIO` in `JobTest` now requires a type parameter which could be either `TableRow` for JSON or `T` for type-safe API where `T` is a type annotated with `@BigQueryType`. Explicit `.map(T.toTableRow)` of test data is no longer needed. See changes in [BigQueryTornadoesTest](https://github.com/spotify/scio/commit/6ded455ba7506e619c484a05db5746cbee6d4dcd#diff-0d0a594c72b702523d4ad2e740253dcc) and [TypedBigQueryTornadoesTest](https://github.com/spotify/scio/commit/6ded455ba7506e619c484a05db5746cbee6d4dcd?diff=split#diff-0aae85e1d761a72c5ab1587fcc797b12) for more.
- Typed `AvroIO` now accepts case classes instead of Avro records in `JobTest`. Explicit `.map(T.toGenericRecord)` of test data is no longer needed. See this [change](https://github.com/spotify/scio/commit/19fee4716f71827ac4affbd23d753bc074c529b8) for more.
- Package `com.spotify.scio.extra.transforms` is moved from `scio-extra` to `scio-core`, under `com.spotify.scio.transforms`.

## Breaking changes since Scio 0.4.0

- Accumulators are replaced by the new metrics API, see [MetricsExample.scala](https://github.com/spotify/scio/blob/master/scio-examples/src/main/scala/com/spotify/scio/examples/extra/MetricsExample.scala) for more
- `com.spotify.scio.hdfs` package and related APIs (`ScioContext#hdfs*`, `SCollection#saveAsHdfs*`) are removed, regular file IO API should now support both GCS and HDFS (if `scio-hdfs` is included as a dependency).
- Starting Scio 0.4.4, Beam runner is completely decoupled from `scio-core`. See [[Runners]] page for more details.

## Breaking changes since Scio 0.3.0

- See this [page](https://cloud.google.com/dataflow/release-notes/release-notes-java-2) for a list of breaking changes from Dataflow Java SDK to Beam
- Scala 2.10 is dropped, 2.11 and 2.12 are the supported Scala binary versions
- Java 7 is dropped and Java 8+ is required
- `DataflowPipelineRunner` is renamed to `DataflowRunner`
- `DirectPipelineRunner` is renamed to `DirectRunner`
- `BlockingDataflowPipelineRunner` is removed and `ScioContext#close()` will not block execution; use `sc.close().waitUntilDone()` to retain the blocking behavior, i.e. if you launch job from an orchestration engine like [Airflow](http://airflow.apache.org/) or [Luigi](https://github.com/spotify/luigi)
- You should set `tempLocation` instead of `stagingLocation` regardless of runner; set it to a local path for `DirectRunner` or a GCS path for `DataflowRunner`; if not set, `DataflowRunner` will create a default bucket for the project
- Type safe BigQuery is now stable API; use `import com.spotify.scio.bigquery._` instead of `import com.spotify.scio.experimental._`
- `scio-bigtable` no longer depends on HBase and uses Protobuf based Bigtable API; check out the updated [example](https://github.com/spotify/scio/blob/master/scio-examples/src/main/scala/com/spotify/scio/examples/extra/BigtableExample.scala)
- Custom IO, i.e. `ScioContext#customInput` and `SCollection#saveAsCustomOutput` require a `name: String` parameter

## Release cycle and backport procedures

Scio has a frequent release cycle, roughly every 2-4 weeks, as compared to months for the upstream Apache Beam. We also aim to stay a step ahead by pulling changes from upstream and contributing new ones back.

Let's call the Beam version that Scio depends on `current`, and upstream master `latest`. Here're the procedures for backporting changes.

For changes available in `latest` but not in `current`:
- Copy Java files from `latest` to Scio repo
- Rename classes and modify as necessary
- Release Scio
- Update checklist for the next `current` version like [#633](https://github.com/spotify/scio/issues/633)
- Remove change once `current` is updated

For changes we want to make to `latest`:
- Submit pull request to `latest`
- Follow the steps above once merged

## Beam master nightly build

To keep up with upstream changes, [beam-master](https://github.com/spotify/scio/tree/beam-master) branch is built nightly and depends on latest Beam SNAPSHOT.

We should do the following periodically to reduce work when upgrading Beam release version.
- rebase `beam-master` on `master`
- fix for breaking changes in `beam-master`
- rebase `master` on `beam-master` when upgrading Beam release version.

To work on a breaking change:
- checkout `beam-master` branch
- run `./scripts/circleci_snapshot.sh` to change `beamVersion` to the latest SNAPSHOT
- run `sbt test it:test` and fix errors