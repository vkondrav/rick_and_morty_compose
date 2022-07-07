package com.vkondrav.ram.navigation

object Routes {

    object Character {
        object All {
            operator fun invoke() = "characters?$NAV_TITLE={$NAV_TITLE}"
        }
        object Details {
            private const val BASE = "character_details"
            operator fun invoke() = "$BASE/{$NAV_ID}?$NAV_TITLE={$NAV_TITLE}"

            operator fun invoke(id: String, title: String) =
                "$BASE/$id?$NAV_TITLE=$title"
        }
    }

    object Episodes {
        object All {
            operator fun invoke() = "episodes?$NAV_TITLE={$NAV_TITLE}"
        }
        object Details {
            private const val BASE = "episode_details"
            operator fun invoke() = "$BASE/{$NAV_ID}?$NAV_TITLE={$NAV_TITLE}"

            operator fun invoke(id: String, title: String) =
                "$BASE/$id?$NAV_TITLE=$title"
        }
    }

    object Locations {
        object All {
            operator fun invoke() = "locations?$NAV_TITLE={$NAV_TITLE}"
        }
        object Details {
            private const val BASE = "location_details"
            operator fun invoke() = "$BASE/{$NAV_ID}?$NAV_TITLE={$NAV_TITLE}"

            operator fun invoke(id: String, title: String) =
                "$BASE/$id?$NAV_TITLE=$title"
        }
    }
}
