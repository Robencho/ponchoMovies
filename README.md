# Poncho - Movies
Poncho Movies es una aplicación para mostrar peliculas a partir de la API The Movie DB
```kotlin
private val moviesViewModel: MoviesViewModel by viewModels()
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
        PonchoMoviesTheme {
            val navController = rememberNavController()
            MoviesNavigationHost(navController = navController, moviesViewModel, false)
        }
    }
}
```
## Alcance del proyecto
Con este proyecto pretendo tener una base de código actualizada, mediante la cual pueda hacer uso 
de las nuevas formas de trabajo sugeridas por la comunidad de desarrolladores de Android.
A medida que se vaya actualizando hiré subiendo los cambios. 