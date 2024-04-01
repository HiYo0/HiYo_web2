import axios from "axios";
import { useEffect, useState } from "react";

export default function BoardView(){

    const [ content , setCotent ] = useState([]);
    
    useEffect(()=>{
        axios.get("/board/get.do")
        .then((r)=>{
            console.log(r)
            const result = r.data.map( (re) =>{return re;})
            setCotent(result)
        })
    },[])

    return(<>
        <div>출력시키기</div>
        
        {content.map((r)=>{
            return(
                <div>
                    작성자 이메일 : {r.memail} /////////내용 : {r.bcontent}
          
                </div>
            )
        })}

       
    </>);
    // <><span>이메일 : {r.memail}</span><span>내용 : {r.bcontent}</span><br /></>
}