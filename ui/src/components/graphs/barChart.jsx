import { Box } from "@mui/material";
import { useEffect, useRef } from "react"

export const BarChart = ({dataSet, setDataSet}) => {

    const barChartRef = useRef(null);
    const containerRef = useRef(null);
    const xGap = 50;
    const yGap = 50;
    const eachBarWidth = 50 ;

    const drawAxis = () => {
        const canvas = barChartRef.current;
        const canvasContext = canvas.getContext('2d');
        canvasContext.beginPath();
        canvasContext.moveTo(xGap, 0);
        canvasContext.lineTo(xGap, canvas.height - 2 * yGap);
        canvasContext.lineTo(canvas.width - xGap, canvas.height - 2 * yGap);
        canvasContext.strokeStyle ="black"
        canvasContext.stroke();

        canvasContext.font = "12px Arial";
        canvasContext.fillStyle = "red";
    }
    
    const renderChart = () => {
        const canvas = barChartRef.current;
        const canvasContext = canvas.getContext('2d');
        drawAxis();

        const maxValueY = Math.max(...dataSet.data.map((dataPoint) => dataPoint.y));
        const tenPercentOfMaxVal =  maxValueY * (10/100);

        const yStep = (canvas.height - 2*yGap) / (maxValueY + tenPercentOfMaxVal);
        const xStep = (canvas.width - xGap) / (dataSet.data.length + 1)

        dataSet.data.forEach((dataPoint, index) => {
            canvasContext.beginPath();
            canvasContext.rect(xStep * index + xGap, (canvas.height - 2*yGap) - (yStep * dataPoint.y), eachBarWidth, (canvas.height - 2*yGap) - ((canvas.height - 2*yGap) - (yStep * dataPoint.y)))
            canvasContext.fill();
            canvasContext.stroke();
        })

    }

    useEffect(() => {
        if (!dataSet) { return; }
        renderChart();
    }, [dataSet])

    return (
       <Box ref={containerRef} sx={{width: "60%", height: "100%", position: "relative"}}>
            <canvas ref={barChartRef} width={1000} height={500}></canvas>
       </Box>
    )
}