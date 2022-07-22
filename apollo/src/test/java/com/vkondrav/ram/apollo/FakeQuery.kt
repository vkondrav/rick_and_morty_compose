@file:Suppress("UtilityClassWithPublicConstructor")
package com.vkondrav.ram.apollo

import com.apollographql.apollo3.api.Adapter
import com.apollographql.apollo3.api.CompiledField
import com.apollographql.apollo3.api.CompiledSelection
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.CustomScalarType
import com.apollographql.apollo3.api.NullableIntAdapter
import com.apollographql.apollo3.api.ObjectType
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.api.json.JsonReader
import com.apollographql.apollo3.api.json.JsonWriter
import com.apollographql.apollo3.api.nullable
import com.apollographql.apollo3.api.obj

class FakeQuery : Query<FakeQuery.Data> {
    override fun equals(other: Any?): Boolean = other != null && other::class == this::class

    override fun hashCode(): Int = this::class.hashCode()

    override fun id(): String = OPERATION_ID

    override fun document(): String = OPERATION_DOCUMENT

    override fun name(): String = OPERATION_NAME

    override fun serializeVariables(
        writer: JsonWriter,
        customScalarAdapters: CustomScalarAdapters,
    ) {
        // no-op
    }

    override fun adapter(): Adapter<Data> = FakeQueryResponseAdapter.Data.obj()

    override fun rootField(): CompiledField = CompiledField.Builder(
        name = "data",
        type = ObjectType(name = "Query"),
    ).selections(selections = FakeQuerySelections.root)
        .build()

    data class Data(
        val fakeNode: FakeNode?,
    ) : Query.Data

    data class FakeNode(
        val fakeObject: FakeObject?,
    )

    data class FakeObject(
        val fakeValue: Int?,
    )

    companion object {
        const val OPERATION_ID: String = "fake_operation_id"

        const val OPERATION_DOCUMENT: String =
            "query FakeQuery { fake_node { fake_object { fake_value } } }"

        const val OPERATION_NAME: String = "FakeQuery"
    }
}

object FakeQueryResponseAdapter {
    object Data : Adapter<FakeQuery.Data> {
        private val RESPONSE_NAMES: List<String> = listOf("fake_node")

        override fun fromJson(
            reader: JsonReader,
            customScalarAdapters: CustomScalarAdapters,
        ): FakeQuery.Data {
            var fakeNode: FakeQuery.FakeNode? = null

            while (true) {
                when (reader.selectName(RESPONSE_NAMES)) {
                    0 ->
                        fakeNode =
                            FakeNode.obj().nullable().fromJson(reader, customScalarAdapters)
                    else -> break
                }
            }

            return FakeQuery.Data(
                fakeNode = fakeNode,
            )
        }

        override fun toJson(
            writer: JsonWriter,
            customScalarAdapters: CustomScalarAdapters,
            `value`: FakeQuery.Data,
        ) {
            writer.name("fake_node")
            FakeNode.obj().nullable().toJson(writer, customScalarAdapters, value.fakeNode)
        }
    }

    object FakeNode : Adapter<FakeQuery.FakeNode> {
        private val RESPONSE_NAMES: List<String> = listOf("fake_object")

        override fun fromJson(
            reader: JsonReader,
            customScalarAdapters: CustomScalarAdapters,
        ): FakeQuery.FakeNode {
            var fakeObject: FakeQuery.FakeObject? = null

            while (true) {
                when (reader.selectName(RESPONSE_NAMES)) {
                    0 -> fakeObject = FakeObject.obj()
                        .nullable().fromJson(reader, customScalarAdapters)
                    else -> break
                }
            }

            return FakeQuery.FakeNode(
                fakeObject = fakeObject,
            )
        }

        override fun toJson(
            writer: JsonWriter,
            customScalarAdapters: CustomScalarAdapters,
            `value`: FakeQuery.FakeNode,
        ) {
            writer.name("fake_object")
            FakeObject.obj().nullable().toJson(writer, customScalarAdapters, value.fakeObject)
        }
    }

    object FakeObject : Adapter<FakeQuery.FakeObject> {
        private val RESPONSE_NAMES: List<String> = listOf("fake_value")

        override fun fromJson(
            reader: JsonReader,
            customScalarAdapters: CustomScalarAdapters,
        ): FakeQuery.FakeObject {
            var count: Int? = null

            while (true) {
                when (reader.selectName(RESPONSE_NAMES)) {
                    0 -> count = NullableIntAdapter.fromJson(reader, customScalarAdapters)
                    else -> break
                }
            }

            return FakeQuery.FakeObject(
                fakeValue = count,
            )
        }

        override fun toJson(
            writer: JsonWriter,
            customScalarAdapters: CustomScalarAdapters,
            `value`: FakeQuery.FakeObject,
        ) {
            writer.name("fake_value")
            NullableIntAdapter.toJson(writer, customScalarAdapters, value.fakeValue)
        }
    }
}

object FakeQuerySelections {
    private val fakeObject: List<CompiledSelection> = listOf(
        CompiledField.Builder(
            name = "fake_value",
            type = GraphQLInt.type,
        ).build(),
    )

    private val fakeNode: List<CompiledSelection> = listOf(
        CompiledField.Builder(
            name = "fake_object",
            type = Info.type,
        ).selections(fakeObject)
            .build(),
    )

    val root: List<CompiledSelection> = listOf(
        CompiledField.Builder(
            name = "fake_node",
            type = FakeNode.type,
        ).selections(fakeNode)
            .build(),
    )
}

class FakeNode {
    companion object {
        val type: ObjectType = ObjectType(name = "FakeNode")
    }
}

class Info {
    companion object {
        val type: ObjectType = ObjectType(name = "FakeObject")
    }
}

class GraphQLInt {
    companion object {
        val type: CustomScalarType = CustomScalarType("Int", "kotlin.Int")
    }
}
