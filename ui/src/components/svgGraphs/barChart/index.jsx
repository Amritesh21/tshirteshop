import { GridLines } from "../drawLines";
import XAxis from "../xAxix"
import { YAxis } from "../yAxis"

export const BarChart = ({data, showGridLines, secondChartWidth, containerPadding}) => {
    console.log(secondChartWidth, ".. client ..");
    const width = secondChartWidth ?? 1000;
    const height = 300;
    const margin = 20;
    const pl = 50, pr = 50;
    const pb = 50, pt = 0;
    const axisColor = "skyblue";
    const axisStrokeWidth = "2";

    let maxValueYAxis = () => {
        const maxYValue = data.reduce((curVal, preVal) => {
            return (curVal.y > preVal.y) ? curVal : preVal
        }, { y: 0 }).y
        return maxYValue + (maxYValue * 10) / 100;
    };

    let maxValueXAxis = () => {
        const maxXValue = data.reduce((curVal, preVal) => {
            return (curVal.x > curVal.y) ? curVal : preVal
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

    console.log(width, "data ... width")

    return (
        <svg width={width} height={height}>
            <XAxis height={height} width={width} pl={pl} pb={pb} strokeWidth={axisStrokeWidth} axisColor={axisColor} />
            <YAxis height={height} width={width} pl={pl} pb={pb} strokeWidth={axisStrokeWidth} axisColor={axisColor} />
            {showGridLines && <GridLines height={height} width={width} pl={pl} pt={pt} pb={pb} gridLineColor="#D3D3D3" gridRatio={10} xScale={xScale} yAxisLablePlot={yAxisLablePlot} />}
            {data.map((point, index) => (
                <g key={index} transform={`translate(${(index + 1) * 70}, ${yScale(point.y) })`}
                    style={{
                        cursor: "pointer"
                    }}
                >
                <title>{`${point.y}, ${point.xlabel}`}</title>
                <rect width="50" height={height - pb - yScale(point.y)} fill="black"></rect>
                <text
                  y={height - pb -yScale(point.y) + 20} x="2"
                        style={{
                            fill: "red",
                            fontSize: "12px",
                        }}
                    >
                        {point.xlabel}
                </text>
            </g>
            ))}
        </svg>
    )
}