function TextInputWithFocusButton(props){
    const inputElem = useRef(null);

    const onButtonClick = ()=>{
        inputElem.current.fucus();
    };

    return(<>
        <input type="text" ref={inputElem} />
        <button onClick={onButtonClick}>Focus the input</button>
    </>);
}