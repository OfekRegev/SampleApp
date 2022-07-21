package com.ofek.sample.presentation.navigation

import com.ofek.sample.domain.feed.FeedType

/**
 * route - the destination navigation route
 * arguments - optional arguments adding to route
 * ancestors - destinations that comes before this destination in the nav graph
 */
sealed class Destination(
    val route: String,
    val arguments: Map<String, String> = emptyMap(),
    val ancestors: List<String> = emptyList()
) {
    override fun toString(): String {
        return "Destination(route='$route', arguments=$arguments, ancestors=$ancestors)"
    }

}

private const val ONBOARDING_NAVIGATION_ROUTE = "onborading"

object OnBoardingDestination : Destination(
    route = ONBOARDING_NAVIGATION_ROUTE,
) {
    const val DECLARATION_ROUTE = ONBOARDING_NAVIGATION_ROUTE
}

sealed class FeedDestination(
    feedType: String,
) : Destination(
    route = FEED_TYPE_ROUTE,
    arguments = mapOf(
        Pair(FEED_TYPE_KEY, feedType)
    ),
    ancestors = listOf(
        OnBoardingDestination.DECLARATION_ROUTE
    )
) {
    companion object {
        const val FEED_TYPE_ROUTE: String = "feed"
        const val FEED_TYPE_VALUE = "value"
        const val FEED_TYPE_KEY = "type"
        private const val FEED_DECLARATION_ROUTE =
            "$FEED_TYPE_ROUTE?$FEED_TYPE_KEY={$FEED_TYPE_VALUE}"
        const val DECLARATION_ROUTE =
            "${OnBoardingDestination.DECLARATION_ROUTE}/$FEED_DECLARATION_ROUTE"

        fun feedTypeFromParam(feedTypeParam: String): FeedType {
            return when (feedTypeParam) {
                STORIES_ROUTE -> {
                    FeedType.STORIES
                }
                ARTICLES_ROUTE -> {
                    FeedType.ARTICLES
                }
                FAVORITES_ROUTE -> {
                    FeedType.FAVORITES
                }
                else -> {
                    // default feed
                    FeedType.ARTICLES
                }
            }
        }
    }
}

private const val STORIES_ROUTE = "stories"

object StoriesDestination : FeedDestination(
    feedType = STORIES_ROUTE,
)

private const val ARTICLES_ROUTE = "articles"

object ArticlesDestination : FeedDestination(
    feedType = ARTICLES_ROUTE,
)

private const val FAVORITES_ROUTE = "favorites"

object FavoritesDestination : FeedDestination(
    feedType = FAVORITES_ROUTE,
)

class PostDestination(
    postId: String = "",
) : Destination(
    route = POST_ID_ROUTE,
    arguments = mapOf(
        Pair(POST_ID_KEY, postId)
    ),
    ancestors = listOf(
        OnBoardingDestination.DECLARATION_ROUTE,
        FeedDestination.FEED_TYPE_ROUTE,
    )
) {
    companion object {
        const val POST_ID_ROUTE: String = "post"
        private const val POST_ID_KEY = "postId"
        const val POST_ID_VALUE = "id"
        private const val POST_ID_ARGUMENT_DECLARATION_KEY =
            "$POST_ID_ROUTE?$POST_ID_KEY={$POST_ID_VALUE}"
        const val DECLARATION_ROUTE =
            "${FeedDestination.DECLARATION_ROUTE}/$POST_ID_ARGUMENT_DECLARATION_KEY"

        fun getPostId(postIdParam: String?): String {
            return postIdParam?.split('=')?.lastOrNull().orEmpty()
        }
    }
}

