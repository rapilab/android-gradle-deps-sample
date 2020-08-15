package com.phodal.gradoid

object AndroidArtifacts {
    private const val TYPE_AAR = "aar"
    private const val TYPE_PROCESSED_AAR = "processed-aar"

    private const val TYPE_JAR = "jar"
    private const val TYPE_PROCESSED_JAR = "processed-jar"


    private const val TYPE_EXPLODED_AAR = "android-exploded-aar"

    enum class ArtifactType(val type: String) {
        AAR(TYPE_AAR),
        PROCESSED_AAR(TYPE_PROCESSED_AAR),

        JAR(TYPE_JAR),
        PROCESSED_JAR(TYPE_PROCESSED_JAR),

        EXPLODED_AAR(TYPE_EXPLODED_AAR),
    }
}
