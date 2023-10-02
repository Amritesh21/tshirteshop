export const GridLines = ({width, height, pl, pb, pt, gridLineColor, gridRatio, xScale, yAxisLablePlot}) => {
    const gridLineCoordinates = []; 
    const horizontalLineGap = (width - pl) / gridRatio;
    const verticalLineGap = (height - pb) / gridRatio;
    for(let i = 0; i<=gridRatio; i++ ) {
        gridLineCoordinates.push({x: (horizontalLineGap * i) + pl || pl, y: (height - pb - (verticalLineGap * i)) })
    }
    console.log(gridLineCoordinates);
    return gridLineCoordinates.map((coordinates) => {
        return (
            <>
             {/* horizontal lines */}
             <line x1={pl - (pl * 30 /100)} y1={coordinates.y} x2={width} y2={coordinates.y} stroke={gridLineColor} strokeWidth="1" />
             <text x={0} y={coordinates.y}
             style={{
                fill: "red",
                fontSize: "12px"
             }}>
                {yAxisLablePlot(coordinates.y)}
            </text>
             {/* vertical lines */}
             <line x1={coordinates.x} y1={pt} x2={coordinates.x} y2={height - pb} stroke={gridLineColor} strokeWidth="1" />
            </>
        )
    })
} 