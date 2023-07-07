import Movie, {MovieDetail} from "./Movie.tsx";

type MovieProps = {
    movies : Array<MovieDetail> |undefined
}
const MoviesBoard = (props : MovieProps)=>{
    return (
        <>
            { props.movies ? (props.movies.length < 1 ? <div className={'loading'}/>:
                <div className={'movie-board'}>
                    {
                        props.movies?.map((movie: MovieDetail, index: number) => (
                                <Movie movie={movie} key={index}></Movie>))
                    }
                </div>
            ) : ""
            }
        </>
    )
}

export default MoviesBoard