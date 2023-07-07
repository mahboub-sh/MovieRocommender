
export type MovieDetail = {
    Title: string
    Year: string
    Poster: string
    Runtime: string
    imdbRating: string
    imdbVotes: string
}

type movieProps = {
    movie: MovieDetail
}

const Movie = (props: movieProps) =>{
    return (
        <>
            <div className={'movie'}>
                <h4 className={'movie__title'}> {props.movie.Title} </h4>
                <img className={'movie__image'} src={props.movie.Poster} alt="movies"/>
                <div>
                    <p> Year: {props.movie.Year} </p>
                    <p> imdb: {props.movie.imdbRating}/10 </p>
                    <p> duration: {props.movie.Runtime}</p>
                </div>
            </div>
        </>
    )
}

export default  Movie ;