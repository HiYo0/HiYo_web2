/* 1. 회원 샘플 데이터 */
insert
    into member( memail , mpassword , mname  )
    values( 'qwe1' , '1234' , '유재석' ),
    ( 'qwe2' , '1234' , '강호동' ),
    ( 'qwe3' , '1234' , '신동엽' ),
    ( 'qwe4' , '1234' , '하하' ),
    ( 'qwe5' , '1234' , '서장훈' );

/* 2. 게시물 샘플 데이터 */
insert
    into board( bcontent , mno_fk )
    values( '게시물내용1' , 1 ),
    ( '게시물내용1' , 2 ),
    ( '게시물내용1' , 3 ),
    ( '게시물내용1' , 4 ),
    ( '게시물내용1' , 5 ),
    ( '게시물내용1' , 1 );

/* 3. 댓글 샘플 데이터 */
insert
    into reply( rcontent , bno_fk , mno_fk )
    values( '댓글1' , 1 , 1 ),
    ( '댓글2' , 1 , 1 ),
    ( '댓글3' , 2 , 2 ),
    ( '댓글4' , 2 , 1 ),
    ( '댓글5' , 3 , 4 ),
    ( '댓글5' , 1 , 5 );
/* 4. 파일 샘플 데이터 */
insert
    into boardfile( bimg , bno_fk )
    values( 'img1.png' , 1 ),
    ( 'img2.png' , 1 ),
    ( 'img3.png' , 2 ),
    ( 'img4.png' , 3 ),
    ( 'img5.png' , 4 ),
    ( '사진6.png' , 5 );