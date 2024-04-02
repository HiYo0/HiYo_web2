import axios from "axios";
import { useRef, useState } from "react";

export default function Write(props){

    // 1. 재렌더링 고정 참조 변수
    const boardWriteFromRef = useRef(); // {current : undefined}
    console.log(boardWriteFromRef);

    console.log(boardWriteFromRef.current);//폼 current 안에 들어있음
    
    const onWrite = ()=>{
        // 1. 전송할 폼 가져오기
        const whiteFrom = document.querySelector('#writeContent'); //'#writeContent' == boardWriteFromRef.current
        // 2. 데이터폼 으로 변환
        const whiteFromData = new FormData(whiteFrom);
        // 3. 서버와 통신
        axios.post('/board/post.do',whiteFromData)
        .then((response)=>{ console.log(response);
            if(response.data){
                alert('글쓰기성공');
                window.location.href ="/board"
            }else{alert("글쓰기실패");window.location.href="/";}
        })
        .catch((e)=>{console.log(e);})
    }
   
    return(<div>
        <form ref={boardWriteFromRef} id="writeContent">
            내용을 입력해주세요 : <input type="text" name="bcontent"/>
            <input type="file" name="uploadList" multiple accept="image/*" />
            <button type="button" onClick={onWrite}>글 작성</button>
        </form>
    </div>);
}