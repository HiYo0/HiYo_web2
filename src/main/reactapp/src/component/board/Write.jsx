import axios from "axios";
import { useState } from "react";

export default function Write(props){
    
    const onWrite = ()=>{
        // 1. 전송할 폼 가져오기
        const whiteFrom = document.querySelector('#writeContent');
        // 2. 데이터폼 으로 변환
        const whiteFromData = new FormData(whiteFrom);
        // 3. 서버와 통신
        axios.post('/board/post.do',whiteFromData)
        .then((response)=>{ console.log(response);
            if(response.data){
                alert('글쓰기성공');
            }else{alert("글쓰기실패");}
            window.location.href="/";

        })
        .catch((e)=>{console.log(e);})

    }

    
    
    return(<>
    
        <form id="writeContent">
            내용을 입력해주세요 : <input type="text" name="bcontent"/>
            <button type="button" onClick={onWrite}>글 작성</button>
        </form>
    </>);
}