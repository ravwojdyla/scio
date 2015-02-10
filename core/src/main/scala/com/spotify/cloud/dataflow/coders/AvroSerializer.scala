package com.spotify.cloud.dataflow.coders

import java.io.ByteArrayOutputStream

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.{Input, Output}
import com.twitter.chill.KSerializer
import org.apache.avro.Schema
import org.apache.avro.io.{DecoderFactory, EncoderFactory}
import org.apache.avro.specific.{SpecificDatumReader, SpecificDatumWriter, SpecificRecord}

import scala.collection.mutable.{Map => MMap}

private class AvroSerializer[T <: SpecificRecord] extends KSerializer[T] {

  private lazy val cache: MMap[Class[_], (SpecificDatumWriter[T], SpecificDatumReader[T])] = MMap()

  private def get(cls: Class[T]) = {
    cache.getOrElseUpdate(cls, {
      val schema = cls.getMethod("getClassSchema").invoke(null).asInstanceOf[Schema]
      (new SpecificDatumWriter[T](schema), new SpecificDatumReader[T](schema))
    })
  }

  override def write(kser: Kryo, out: Output, obj: T): Unit = {
    val writer = get(obj.getClass.asInstanceOf[Class[T]])._1

    val stream = new ByteArrayOutputStream()
    val encoder = EncoderFactory.get().binaryEncoder(stream, null)
    writer.write(obj, encoder)
    encoder.flush()

    out.writeInt(stream.size())
    out.writeBytes(stream.toByteArray)
    out.flush()
  }

  override def read(kser: Kryo, in: Input, cls: Class[T]): T = {
    val reader = get(cls)._2

    val bytes = Array.ofDim[Byte](in.readInt())
    in.readBytes(bytes)

    val decoder = DecoderFactory.get().binaryDecoder(bytes, null)
    reader.read(null.asInstanceOf[T], decoder)
  }

}
