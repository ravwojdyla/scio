---
layout: home
title: Home
---

> Ecclesiastical Latin IPA: /ˈʃi.o/, [ˈʃiː.o], [ˈʃi.i̯o]

> Verb: I can, know, understand, have knowledge.

Scio is a Scala API for [Apache Beam](https://beam.apache.org/) and [Google Cloud Dataflow](https://github.com/GoogleCloudPlatform/DataflowJavaSDK) inspired by [Apache Spark](http://spark.apache.org/) and [Scalding](https://github.com/twitter/scalding).

Scio `0.3.0` and future versions depend on Apache Beam (`org.apache.beam`) while earlier versions depend on Google Cloud Dataflow SDK (`com.google.cloud.dataflow`). See this [page](https://github.com/spotify/scio/wiki/Apache-Beam) for a list of breaking changes.

[[Getting Started]] is the best place to start with Scio. If you are new to Apache Beam and distributed data processing, check out the [Beam Programming Guide](https://beam.apache.org/documentation/programming-guide/) first for a detailed explanation of the Beam programming model and concepts. If you have experience with other Scala data processing libraries, check out this comparison between [[Scio, Scalding and Spark]]. Finally check out this document about the relationship between [[Scio, Beam and Dataflow]].

Example Scio pipelines and tests can be found under [scio-examples](https://github.com/spotify/scio/tree/master/scio-examples/src). A lot of them are direct ports from Beam's Java [examples](https://github.com/apache/beam/tree/master/examples). See this [page](http://spotify.github.io/scio/examples/) for some of them with side-by-side explanation. Also see [Big Data Rosetta Code](https://github.com/spotify/big-data-rosetta-code) for common data processing code snippets in Scio, Scalding and Spark.

See [Scio Scaladocs](http://spotify.github.io/scio) for current API documentation.

## Getting help
- [![Join the chat at https://gitter.im/spotify/scio](https://badges.gitter.im/spotify/scio.svg)](https://gitter.im/spotify/scio)
- [![Google Group](https://img.shields.io/badge/Google%20Group-scio--users-blue.svg)](https://groups.google.com/forum/#!forum/scio-users)
- [![Stack Overflow](https://img.shields.io/badge/Stack%20Overflow-spotify--scio-yellow.svg)](http://stackoverflow.com/questions/tagged/spotify-scio)

## Further Readings
  - [Big Data Processing at Spotify: The Road to Scio (Part 1)](https://labs.spotify.com/2017/10/16/big-data-processing-at-spotify-the-road-to-scio-part-1/)
  - [Big Data Processing at Spotify: The Road to Scio (Part 2)](https://labs.spotify.com/2017/10/23/big-data-processing-at-spotify-the-road-to-scio-part-2/)
  - [The world beyond batch: Streaming 101](https://www.oreilly.com/ideas/the-world-beyond-batch-streaming-101)
  - [The world beyond batch: Streaming 102](https://www.oreilly.com/ideas/the-world-beyond-batch-streaming-102)
  - [Dataflow/Beam & Spark: A Programming Model Comparison](https://cloud.google.com/dataflow/blog/dataflow-beam-and-spark-comparison)
  - [VLDB paper](http://www.vldb.org/pvldb/vol8/p1792-Akidau.pdf) on the Dataflow Model

## Presentations
  - [Sorry - How Bieber broke Google Cloud at Spotify](https://www.slideshare.net/sinisalyh/sorry-how-bieber-broke-google-cloud-at-spotify) - Scala Up North 2017 Talk
  - [Scio - Moving to Google Cloud A Spotify Story](https://www.slideshare.net/sinisalyh/scio-moving-to-google-cloud-a-spotify-story) - Philly ETE 2017 Talk
  - [Scio - A Scala API for Google Cloud Dataflow & Apache Beam](http://www.slideshare.net/sinisalyh/scio-a-scala-api-for-google-cloud-dataflow-apache-beam) - Scala by the Bay 2016 Talk
  - [From stream to recommendation with Cloud Pub/Sub and Cloud Dataflow](https://www.youtube.com/watch?v=xT6tQAIywFQ) - GCP NEXT 16 Talk
  - [Apache Beam Presentation Materials](https://beam.apache.org/contribute/presentation-materials/)

## Scio Development
  - [[Style Guide]]
  - [[How to Release]]
  - [[Design Philosophy]]

## Projects using or related to Scio
  - [Featran](https://github.com/spotify/featran) - A Scala feature transformation library for data science and machine learning
  - [Big Data Rosetta Code](https://github.com/spotify/big-data-rosetta-code) - Code snippets for solving common big data problems in various platforms. Inspired by [Rosetta Code](http://rosettacode.org/)
  - [Ratatool](https://github.com/spotify/ratatool) - A tool for random data sampling and generation, which includes [BigDiffy](https://github.com/spotify/ratatool/blob/master/ratatool-diffy/src/main/scala/com/spotify/ratatool/diffy/BigDiffy.scala), a Scio library for pairwise field-level statistical diff of data sets ([slides](http://www.lyh.me/slides/bigdiffy.html))
  - [scio-deep-dive](https://github.com/nevillelyh/scio-deep-dive) - Building Scio from scratch step by step for an internal training session
  - [scala-flow](https://github.com/zendesk/scala-flow) - A lightweight Scala wrapper for Google Cloud Dataflow from Zendesk
  - [clj-headlights](https://github.com/zendesk/clj-headlights) - Clojure API for Apache Beam, also from Zendesk
  - [datasplash](https://github.com/ngrunwald/datasplash) - A Clojure API for Google Cloud Dataflow
