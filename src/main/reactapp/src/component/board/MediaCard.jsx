import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

import { useContext } from 'react';
import { LoginInfoContext } from '../Index';

export default function MediaCard(props) {

  
  const navigate = useNavigate();

  const {loginInfo} = useContext(LoginInfoContext);

    console.log(props);// { 'board' : board객체}
    // 삭제기능
    const deleteBtn = (event , bno , mno_fk)=>{
      console.log("삭제버튼누름");
      console.log(loginInfo);
      // if(작성자회원!=로그인회원)
      if(mno_fk!=loginInfo.mno){return;}
      
      
      // console.log(props.board.bno);

      // let info = { "bno" : props.board.bno }
      axios.delete("/board/delete.do",{params : {bno : props.board.bno}})
      .then((r)=>{
        console.log(r);
        // 1 삭제성공
        // 0 삭제실패
        // -1 다른사람꺼임 아이디 불일치
        if(r.data == 1){alert("삭제성공")}
        else if(r.data == -1){alert("작성자 와 요청자 아이디가 다름")}
        else{alert("삭제실패")}
        // 1. get 방식
        window.location.href = "/board";
        // 2. 라우터 방식
          // 1. useNavigate() 훅 필요함
            // import { useNavigate } from 'react-router-dom';
          // 2. navigate( 라우터 URL );
        // navigate('/board');
        // 3. props 방식 (함수를 따로 빼서 매개변수로 전달해서 재호출)
        
      })
      .catch((re)=>{console.log(re);})

    }

  return (
    <Card sx={{ maxWidth: 400, margin:1}}>
      <CardMedia
        sx={{ height: 140 }}
        image={"/uploadimg/"+ (props.board.bimglist[0] != undefined ? props.board.bimglist[0] : 'default.png' ) }
        title="green iguana"
      />
      {console.log(props.board.bimglist[0])}
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
            {props.board.memail}
        </Typography>
        <Typography variant="body2" color="text.secondary">
            {props.board.bcontent}
        </Typography>
      </CardContent>
      <CardActions>
        {loginInfo.mno == props.board.mno_fk &&
          <Button size="small" onClick={(event)=> deleteBtn (event , props.board.bno , props.board.mno_fk) }>삭제버튼</Button>
        }
        <Button size="small">Learn More</Button>
      </CardActions>
    </Card>
  );
}