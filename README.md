# Paging3 Image Loading Example

This repository demonstrates how to implement efficient pagination with image loading in an Android app using [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview), [Kotlin](https://kotlinlang.org/), and [Jetpack Compose](https://developer.android.com/jetpack/compose).

## Features

- **Pagination with Paging3**: Seamlessly load data page by page as the user scrolls.
- **Jetpack Compose UI**: Modern, declarative UI with Compose.
- **Image Loading**: Efficient and smooth image loading for paged items.
- **Kotlin**: Entire project written in idiomatic Kotlin.

## Sample

https://github.com/user-attachments/assets/3ee01a34-0263-4e2b-89c5-fcdedecebda3

## Getting Started

### Prerequisites

- Android Studio (Chipmunk or newer recommended)
- Minimum SDK 21+
- Kotlin 1.7 or newer

### How to Run

1. **Clone the repository**:
    ```bash
    git clone https://github.com/zahid-git/Paging3-Image-Loading.git
    ```
2. **Open in Android Studio**:
    - Select `Open an existing Android Studio project` and navigate to the cloned folder.
3. **Build and Run**:
    - Click the 'Run' button or use `Shift+F10` to build and launch the app on your device or emulator.

## Key Libraries Used

- [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Coil](https://coil-kt.github.io/coil/) or your preferred image loader
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

## Example Usage

The app fetches a list of images (e.g., from a mock API), displays them in a vertical list, and automatically loads more as you scroll.

```kotlin
val pager = Pager(
    config = PagingConfig(pageSize = 20),
    pagingSourceFactory = { ImagePagingSource(api) }
).flow.cachedIn(viewModelScope)
```

## Full Instruction

[Check on Medium](https://medium.com/@me.zahidul/mastering-android-pagination-with-paging-3-jetpack-compose-9c8bad8ee98f)
