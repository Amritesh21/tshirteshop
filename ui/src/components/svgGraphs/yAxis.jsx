export const YAxis = ({
    height, pl = 0, pb = 0, pt = 0, strokeWidth="1", axisColor="black"
}) => {
     
    const x = pl;
    const y1 = pt, y2 = height - pb;

    return(
        <line x1={x} y1={y1} x2={x} y2={y2} stroke={axisColor} strokeWidth={strokeWidth}  />
    )
}

export const verticalGridLines = ({
    height, pl, pb , pt, strokeWidth = "1", axisColor = "gray", grid
}) => {

    return(
       <>
         
       </> 
    )

}