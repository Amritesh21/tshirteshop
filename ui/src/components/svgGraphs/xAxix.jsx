import { useEffect } from "react";
import { useState } from "react";

const XAxis = ({
    width, height, pl, pb, strokeWidth="1", axisColor = black, containerPadding
}) => {

    // const [x2, setX2] = useState(width);
    const x1 = pl, x2 = containerPadding ? width - containerPadding : width;
    const y = height - pb

    console.log(width, "....")

    // useEffect(() => {
    //     setX2(width);
    //     console.log(width, "....")
    // }, [width]);

    return (
        <line x1={x1} y1={y} x2={x2} y2={y} stroke={axisColor} strokeWidth={strokeWidth} />
    )
}

export default XAxis;