import { useEffect, useState } from "react";
import XAxis from "../xAxix"
import { YAxis } from "../yAxis"
import { GridLines } from "../drawLines";

export const LineChart = ({ data, showGridLines, firstChartWidth,firstChartPadding }) => {
    console.log(firstChartWidth, "line chart width")
    const [animationStarted, setAnimationStarted] = useState(false);
    const width = firstChartWidth ?? 1000;
    const height = 300;
    const margin = 20;
    const pl = 50, pr = 50;
    const pb = 50, pt = 0;
    const axisColor = "#D3D3D3";
    const axisStrokeWidth = "2";

    let maxValueYAxis = () => {
        const maxYValue = data.reduce((curVal, preVal) => {
            return (curVal.y > preVal.y) ? curVal : preVal
        }, { y: 0 }).y
        return maxYValue + (maxYValue * 10) / 100;
    };

    let maxValueXAxis = () => {
        const maxXValue = data.reduce((curVal, preVal) => {
            return (curVal.x > preVal.x) ? curVal : preVal
        }, { x: 0 }).x;
        return maxXValue + (maxXValue * 10) / 100;
    };

    const yScale = (y) => (height - pb) - (((height - pb) / maxValueYAxis()) * y);
    const xScale = (x) => ((width - pl) / (maxValueXAxis()) * x) + pl;

    const yAxisLablePlot = (yCoordinate) => (((height - pb) - yCoordinate) * (maxValueYAxis()) / (height - pb))

    const colorCondition = (value) => {
        if (value <= 1000) return "green";
        else if (value > 1000 && value <= 3000) return "yellow";
        else if (value > 3000) return "red";
    }

    /*
    yAxis
     formula 1 :- (height - pb ) - (((height - pb) / maxValueYAxis) * yValue) 
     formula 2 :- height - (yValue / maxValueYAxis) * height + pb

     xAxis
     formula 1 :- (xValue) / maxValueXAxis * width + padding
     formula 2 :- (((width - padding) / maxValueXAxis) * xValue) + padding
    */

    const getPlots = () => data.map((element) => {
        const plot = `${xScale(element.x)} ${yScale(element.y)}`
        console.log(plot,maxValueXAxis());
        return plot;

    }).join();


    useEffect(() => {
        if (!animationStarted && firstChartWidth) {
            setAnimationStarted(false);
            const path = document.querySelector('#graph-path');
            const length = path.getTotalLength();
            path.style.transition = 'none';
            path.style.strokeDasharray = length;
            path.style.strokeDashoffset = length;
            // Trigger the animation
            path.getBoundingClientRect();
            setTimeout(() => {
                path.style.transition = 'stroke-dashoffset 2s ease-out';
                path.style.strokeDashoffset = 0;
            }, 100)

        }
    }, [animationStarted, firstChartWidth]);

    useEffect(() => {
        setAnimationStarted(true);
    }, []);

    return (
        <svg width={width} height={height}>
            <XAxis height={height} width={width} pl={pl} pb={pb} strokeWidth={axisStrokeWidth} axisColor={axisColor} />
            <YAxis height={height} width={width} pl={pl} pb={pb} strokeWidth={axisStrokeWidth} axisColor={axisColor} />
            {showGridLines && <GridLines height={height} width={width} pl={pl} pt={pt} pb={pb} gridLineColor="#D3D3D3" gridRatio={10} xScale={xScale} yAxisLablePlot={yAxisLablePlot} />}
            <polyline id="graph-path" points={getPlots()} style={{ fill: "none", stroke: "lightblue", strokeWidth: "1" }} />
            {data.map((point, index) => (
                <g key={index}>
                    <title>{`${point.y}, ${point.xlabel}`}</title>
                    <circle key={`${point.x}-${point.y}`} cx={xScale(point.x)} cy={yScale(point.y)} r="4" fill={colorCondition(point.y)}
                      style={{
                        cursor: "pointer"
                      }}
                    />
                    <text x={xScale(point.x)} y={height - pb + 15}
                        style={{
                            fill: "red",
                            fontSize: "12px"
                        }}
                    >
                        {point.xlabel}
                    </text>
                </g>
            ))}
        </svg>
    )
}