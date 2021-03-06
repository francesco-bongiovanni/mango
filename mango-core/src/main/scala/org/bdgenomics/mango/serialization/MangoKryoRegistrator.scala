/**
 * Licensed to Big Data Genomics (BDG) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The BDG licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bdgenomics.mango.serialization

import com.esotericsoftware.kryo.Kryo
import org.apache.spark.serializer.KryoRegistrator
import org.bdgenomics.adam.models._
import org.bdgenomics.adam.rdd.read.realignment._
import org.bdgenomics.adam.serialization._
import org.bdgenomics.adam.util.{ TwoBitFile, TwoBitFileSerializer }
import org.bdgenomics.formats.avro._
import org.bdgenomics.mango.layout.CalculatedAlignmentRecord
import org.bdgenomics.mango.tiling.ReferenceTile

import scala.collection.mutable.{ ArrayBuffer, ListBuffer }

class MangoKryoRegistrator extends KryoRegistrator {
  override def registerClasses(kryo: Kryo): Unit = {
    kryo.register(classOf[ListBuffer[AlignmentRecord]])
    kryo.register(classOf[ArrayBuffer[AlignmentRecord]])
    kryo.register(classOf[ListBuffer[Genotype]])
    kryo.register(classOf[ArrayBuffer[Genotype]])

    kryo.register(classOf[ReferenceTile])
    kryo.register(classOf[CalculatedAlignmentRecord])

    kryo.register(classOf[AlignmentRecord], new AvroSerializer[AlignmentRecord]())
    kryo.register(classOf[Genotype], new AvroSerializer[Genotype]())
    kryo.register(classOf[Variant], new AvroSerializer[Variant]())
    kryo.register(classOf[DatabaseVariantAnnotation], new AvroSerializer[DatabaseVariantAnnotation]())
    kryo.register(classOf[NucleotideContigFragment], new AvroSerializer[NucleotideContigFragment]())
    kryo.register(classOf[Contig], new AvroSerializer[Contig]())
    kryo.register(classOf[RecordGroupMetadata], new AvroSerializer[RecordGroupMetadata]())
    kryo.register(classOf[StructuralVariant], new AvroSerializer[StructuralVariant]())
    kryo.register(classOf[VariantCallingAnnotations], new AvroSerializer[VariantCallingAnnotations]())
    kryo.register(classOf[DatabaseVariantAnnotation], new AvroSerializer[DatabaseVariantAnnotation]())
    kryo.register(classOf[Dbxref], new AvroSerializer[Dbxref]())
    kryo.register(classOf[Feature], new AvroSerializer[Feature]())
    kryo.register(classOf[ReferencePosition], new ReferencePositionSerializer)
    kryo.register(classOf[ReferencePositionPair], new ReferencePositionPairSerializer)
    kryo.register(classOf[SingleReadBucket], new SingleReadBucketSerializer)
    kryo.register(classOf[IndelRealignmentTarget])
    kryo.register(classOf[TargetSet], new TargetSetSerializer)
    kryo.register(classOf[ZippedTargetSet], new ZippedTargetSetSerializer)
    kryo.register(classOf[TwoBitFile], new TwoBitFileSerializer)
  }
}
