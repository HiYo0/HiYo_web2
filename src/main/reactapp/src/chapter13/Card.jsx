export default function Card(props){
    // ??
    const { title , backgroundColor , children } = props;
    

    // title 값이 있으면 title출력
    // 자식요소 출력
    return(<>
        <div
            style={{
                maxWidth : 600,
                margin : 8,
                padding : 8,
                borderRadius : 8,
                boxShadow : "0px 0px 4px gray",
                background : backgroundColor || "white",
            }}
        >
            {title && <h1>{title}</h1>}
            {children}
        </div>
    </>);
}