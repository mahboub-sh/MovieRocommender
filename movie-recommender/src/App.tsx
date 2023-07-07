import './App.css'
import PreferenceBoard from "./PreferenceBoard.tsx";
import {useState} from "react";
import MoviesBoard from "./MoviesBoard.tsx";
import {MovieDetail} from "./Movie.tsx";




function App() {
    const [movies, setMovies] = useState<Array<MovieDetail>>()

    const clearMovies = () => {
        setMovies([])
    }
    const fetchMovies = (movies:Array<MovieDetail>) => {
        setMovies(movies)
    }

  return (
    <>
        <PreferenceBoard fetchMovies={fetchMovies} clearMovies={clearMovies}></PreferenceBoard>
        <hr/>
        <MoviesBoard movies={movies} ></MoviesBoard>
    </>
  )
}

export default App
