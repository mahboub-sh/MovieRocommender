import {useRef} from "react";
import {MovieDetail} from "./Movie.tsx";

export type PreferenceProps = {
    fetchMovies: (arg: Array<MovieDetail>) => void
    clearMovies: () => void
}

const PreferenceBoard= (props:PreferenceProps) =>{
    const genreSelectEl = useRef<HTMLSelectElement>(null)
    const durationSelectEl= useRef<HTMLSelectElement>(null)

    const getMovies = async (genres:string|undefined, duration:string|undefined)=> {
        const data = await fetch(
            `http://localhost:8080/hello`,
        {
                method: "POST",
                body: JSON.stringify({genres: genres, duration: duration}),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }
        )
        const response:Array<MovieDetail> = await data.json()
        return response
    }

    const onClickFunc = () => {
        props.clearMovies();
        const genres: string | undefined = genreSelectEl.current?.value
        const duration:string | undefined = durationSelectEl.current?.value
        getMovies(genres, duration).then((movies: Array<MovieDetail>) => props.fetchMovies(movies))
    }

    return(
        <>
            <form className={'preferenceBoard'}>
                <div className={'preferenceBoard__filter'}>
                    <label>Genres </label>
                    <select  ref={genreSelectEl} >
                        <option value="action">Action</option>
                        <option value="animation">Animation</option>
                        <option value="comedy">Comedy</option>
                    </select>
                </div>
                <div className={'preferenceBoard__filter'}>
                    <label>Duration</label>
                    <select ref={durationSelectEl}>
                        <option value="less than 90 minutes" >Short (less than 90 minutes)</option>
                        <option value="90 to 120 minutes" >Medium (90 to 120 minutes)</option>
                        <option value="120 to 150 minutes" >Standard (120 to 150 minutes)</option>
                        <option value="more than 150 minutes" >Long (more than 150 minutes)</option>
                    </select>
                </div>
                <input type="button" onClick={onClickFunc} value={"Search"}/>
            </form>
        </>
    )
}

export default PreferenceBoard