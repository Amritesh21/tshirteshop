import React from 'react';

const data = [
  { x: 0, y: 5 },
  { x: 1, y: 8 },
  { x: 2, y: 3 },
  { x: 3, y: 10 },
  { x: 4, y: 6 },
];

const svgGraph = () => {
  const width = 400;
  const height = 300;
  const margin = 20;
  
  const xScale = (x) => x * (width - 2 * margin) / (data.length - 1);
  const yScale = (y) => height - margin - y * (height - 2 * margin) / 10;
  
  const pathData = `M ${xScale(data[0].x)} ${yScale(data[0].y)} ${data.map(point => `L ${xScale(point.x)} ${yScale(point.y)}`).join(' ')}`;

  return (
    <svg width={width} height={height}>
      {/* x-axis */}
      <line x1={margin} y1={height - margin} x2={width - margin} y2={height - margin} stroke="black" strokeWidth="1" />
      {/* y-axis */}
      <line x1={margin} y1={margin} x2={margin} y2={height - margin} stroke="black" strokeWidth="1" />

      <path d={pathData} fill="none" stroke="blue" strokeWidth="2" />
      {data.map(point => (
        <circle key={`${point.x}-${point.y}`} cx={xScale(point.x)} cy={yScale(point.y)} r="4" fill="blue" />
      ))}
    </svg>
  );
};

export default svgGraph;
