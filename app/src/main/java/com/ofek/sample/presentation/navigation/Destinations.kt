package com.ofek.sample.presentation.navigation

import com.ofek.sample.domain.feed.FeedType

/**
 * route - the destination navigation route
 * arguments - optional arguments adding to route
 */
sealed class Destination(
    val route: String,
    val ancestors: List<String> = emptyList()
) {
    override fun toString(): String {
        return "Destination(" +
                "route='$route', " +
                "ancestors=$ancestors" +
                ")"
    }
}

private const val ONBOARDING_NAVIGATION_ROUTE = "onborading"

object OnBoardingDestination : Destination(
    route = ONBOARDING_NAVIGATION_ROUTE,
) {
    const val DECLARATION_ROUTE = ONBOARDING_NAVIGATION_ROUTE
}

sealed class FeedDestination(
    route: String,
) : Destination(
    route = "$FEED_TYPE_ROUTE=$route",
    ancestors = listOf(
        OnBoardingDestination.DECLARATION_ROUTE
    )
) {
    companion object {
        const val FEED_TYPE_ROUTE: String = "feedType"
        private const val FEED_TPYE_VALUE = "type"
        const val FEED_TYPE_ARGUMENT_DECLARATION_KEY = "$FEED_TYPE_ROUTE=$FEED_TPYE_VALUE"
        const val DECLARATION_ROUTE =
            "${OnBoardingDestination.DECLARATION_ROUTE}/{$FEED_TYPE_ARGUMENT_DECLARATION_KEY}"

        fun routeToFeedType(feedTypeParam: String): FeedType {
            return when (feedTypeParam.split('=').lastOrNull().orEmpty()) {
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
    route = STORIES_ROUTE,
)

private const val ARTICLES_ROUTE = "articles"

object ArticlesDestination : FeedDestination(
    route = ARTICLES_ROUTE,
)

private const val FAVORITES_ROUTE = "favorites"

object FavoritesDestination : FeedDestination(
    route = FAVORITES_ROUTE,
)

class PostDestination(
    postId: String = "",
) : Destination(
    route = "$POST_ID_ROUTE=$postId",
    ancestors = listOf(
        OnBoardingDestination.DECLARATION_ROUTE,
        FeedDestination.FEED_TYPE_ROUTE,
    )
) {
    companion object {
        const val POST_ID_ROUTE: String = "post"
        private const val POST_ID_VALUE = "id"
        const val POST_ID_ARGUMENT_DECLARATION_KEY = "$POST_ID_ROUTE=$POST_ID_VALUE"
        const val DECLARATION_ROUTE =
            "${FeedDestination.DECLARATION_ROUTE}/{$POST_ID_ARGUMENT_DECLARATION_KEY}"

        fun getPostId(postIdParam: String?): String {
            return postIdParam?.split('=')?.lastOrNull().orEmpty()
        }
    }
}

