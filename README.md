Rick And Morty Compose
=========

This is a Sample App utilizing some of the latest android technologies including

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Apollo Kotlin 3](https://www.apollographql.com/docs/kotlin/)
- [Room](https://developer.android.com/jetpack/androidx/releases/room)

All external data is from [Rick and Morty GraphQL API](https://rickandmortyapi.com/graphql)

https://user-images.githubusercontent.com/4791730/175387256-532cf86b-3a27-4245-8cba-8a06b1345e45.mp4

## Module Structure
Click on each for more info

```mermaid
  graph TD;
      U[Common-Util]
      T[Common-Test]
      L[Apollo]-->G[GraphQL]
      R[Room]-->D[Domain];
      G-->D;
      D-->A[App];
      click L "https://github.com/vkondrav/rick_and_morty_compose/tree/main/apollo" "Go to module"
      click G "https://github.com/vkondrav/rick_and_morty_compose/tree/main/graphql" "Go to module"
      click R "https://github.com/vkondrav/rick_and_morty_compose/tree/main/room" "Go to module"
      click D "https://github.com/vkondrav/rick_and_morty_compose/tree/main/domain" "Go to module"
      click A "https://github.com/vkondrav/rick_and_morty_compose/tree/main/app" "Go to module"
      click U "https://github.com/vkondrav/rick_and_morty_compose/tree/main/common-util" "Go to module"
      click T "https://github.com/vkondrav/rick_and_morty_compose/tree/main/common-test" "Go to module"
```

## MAD Scorecard
![Summary](/mad_scorecard/summary.png?raw=true)
![Summary](/mad_scorecard/jetpack.png?raw=true)
![Summary](/mad_scorecard/kotlin.png?raw=true)
![Summary](/mad_scorecard/studio.png?raw=true)
