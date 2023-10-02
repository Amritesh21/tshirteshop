import { BarChart } from '@/components/svgGraphs/barChart';
import { LineChart } from '@/components/svgGraphs/lineChart';
import XAxis from '@/components/svgGraphs/xAxix';
import { YAxis } from '@/components/svgGraphs/yAxis';
import { Button } from '@mui/material';
import React, { useState } from 'react';

const data = [
  { x: 0, y: 5000, xLabel: "sunday"},
  { x: 1, y: 8 , xLabel: "moday"},
  { x: 2, y: 3000, xLabel: "tudesday"},
  { x: 3, y: 10, xLabel: "wednesday"},
  { x: 4, y: 600, xLabel: "thursday"},
  { x: 5, y: 5000, xLabel: "friday"},
  { x: 6, y: 8, xLabel: "saturday"},
  { x: 7, y: 3000, xLabel: "sunday"},
  { x: 8, y: 10, xLabel: "monday"},
  { x: 9, y: 600, xLabel: "tuesday"},
];

const SvgGraph = () => {

  const [showGridLines, setShowGridLines] = useState(false);

  return (
    <>
      {/* x-axis */}
      {/* <line x1={margin} y1={height - margin} x2={width - margin} y2={height - margin} stroke="black" strokeWidth="1" /> */}
      {/* y-axis */}
      {/* <line x1={margin} y1={margin} x2={margin} y2={height - margin} stroke="black" strokeWidth="1" />

      <path d={pathData} fill="none" stroke="blue" strokeWidth="2" />
      <path d={'M 0 0 L 100 200 '} fill="none" stroke="blue" strokeWidth="2" />
      {data.map(point => (
        <circle key={`${point.x}-${point.y}`} cx={xScale(point.x)} cy={yScale(point.y)} r="4" fill="blue" />
      ))} */}
      <Button onClick={() => setShowGridLines(!showGridLines)}>Show Grid Lines</Button>
    </>
  );
};

export default SvgGraph;
