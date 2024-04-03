import axios from "axios";
import React, { useEffect, useState } from "react";
import MediaCard from "./MediaCard";
import { Pagination } from "@mui/material";

export default function BoardView(props){

    const [ pageDto , setPageDto ] = useState({"page":1 , "count" : 0,"data":[]});

    
    const handleChange = (event, value) => {
        pageDto.page=value;
            setPageDto({...pageDto});
        };


    
    useEffect(()=>{
        const info = {page : pageDto.page , view : 2}
        axios.get(`/board/get.do`,{params:info})
        .then((r)=>{
            console.log(r);
            // const result = r.data.map( (re) =>{return re;});
            setPageDto(r.data);
        })
    },[pageDto.page])
    

    

    return(<>
        <div style={{display:"flex" ,flexWrap : "wrap"}}>
        
        {
        pageDto.data.map((r)=>{
            return(
                <MediaCard board={r} setPageDto = {setPageDto}/>
            )
        })
        }

        </div>
        <ul>
            <Pagination count={ pageDto.count } page={pageDto.page} onChange={handleChange} />
        </ul>

       
    </>);
    // <><span>이메일 : {r.memail}</span><span>내용 : {r.bcontent}</span><br /></>
}