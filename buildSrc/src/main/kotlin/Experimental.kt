object Experimental {
    private const val pager = "com.google.accompanist.pager.ExperimentalPagerApi"
    private const val animation = "androidx.compose.animation.ExperimentalAnimationApi"
    private const val navigation =
        "com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi"
    private const val material = "androidx.compose.material.ExperimentalMaterialApi"
    private const val coroutines = "kotlinx.coroutines.ExperimentalCoroutinesApi"
    private const val apollo = "com.apollographql.apollo3.annotations.ApolloExperimental"
    private const val contracts = "kotlin.contracts.ExperimentalContracts"

    val optIns = listOf(
        pager,
        animation,
        navigation,
        material,
        coroutines,
        apollo,
        contracts,
    ).joinToString(separator = ",")
}
