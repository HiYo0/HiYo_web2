import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

export default function MediaCard(props) {

    console.log(props);// { 'board' : board객체}

  return (
    <Card sx={{ maxWidth: 400 }}>
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
        <Button size="small">Share</Button>
        <Button size="small">Learn More</Button>
      </CardActions>
    </Card>
  );
}