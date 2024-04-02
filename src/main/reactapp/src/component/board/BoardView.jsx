import axios from "axios";
import { useEffect, useState } from "react";
import MediaCard from "./MediaCard";

export default function BoardView(props){

    const [ content , setCotent ] = useState([]);


    
    useEffect(()=>{
        axios.get("/board/get.do")
        .then((r)=>{
            console.log(r);
            const result = r.data.map( (re) =>{return re;});
            setCotent(result);
        })
    },[])
    

    

    return(<>
        <div style={{display:"flex"}}>
        
        {
        content.map((r)=>{
            return(
                <MediaCard board={r}/>
            )
        })
        }

        </div>

       
    </>);
    // <><span>이메일 : {r.memail}</span><span>내용 : {r.bcontent}</span><br /></>
}