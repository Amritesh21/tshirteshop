import { Box, Button } from "@mui/material";
import { useEffect, useRef } from "react";

const GraphPage = () => {
    const canvasRef = useRef(null);

    // useEffect(() => {
    //     const canvas = canvasRef.current;
    //     const context = canvas.getContext('2d');
    
    //     // Sample data
    //     const data = [12, 19, 3, 5, 2];
    //     const labels = ['January', 'February', 'March', 'April', 'May'];
    
    //     const width = canvas.width;
    //     const height = canvas.height;
    
    //     // Draw the axes
    //     context.beginPath();
    //     context.moveTo(30, 0);
    //     context.lineTo(30, height - 30);
    //     context.lineTo(width, height - 30);
    //     context.stroke();

    //     const numHorizontalGridLines = 5;
    //     const stepY2 = (height - 30) / numHorizontalGridLines;
    //     for (let i = 1; i <= numHorizontalGridLines; i++) {
    //     context.beginPath();
    //     context.moveTo(30, i * stepY2);
    //     context.lineTo(width, i * stepY2);
    //     context.strokeStyle = '#ddd';
    //     context.stroke();
    //     }

    //     context.fillStyle = 'black';
    //     context.font = '12px Arial';
    //     context.textAlign = 'right';
    //     for (let i = 0; i <= numHorizontalGridLines; i++) {
    //     const label = (numHorizontalGridLines - i) * (data.length / numHorizontalGridLines);
    //     context.fillText(label, 25, i * stepY2 + 5);
    //     }
    
    //     // Calculate data points and draw the line graph
    //     const stepX = (width - 30) / (labels.length - 1);
    //     const maxValue = Math.max(...data);
    //     const stepY = (height - 30) / maxValue;
    
    //     context.beginPath();
    //     context.moveTo(30, height - data[0] * stepY);
    //     for (let i = 1; i < data.length; i++) {
    //       context.lineTo(30 + i * stepX, height - data[i] * stepY);
    //     }
    //     context.stroke();
    
    //     // Draw data points
    //     context.fillStyle = 'blue';
    //     for (let i = 0; i < data.length; i++) {
    //       context.beginPath();
    //       context.arc(30 + i * stepX, height - data[i] * stepY, 5, 0, Math.PI * 2);
    //       context.fill();
    //     }
    
    //     // Draw labels
    //     context.fillStyle = 'black';
    //     context.font = '12px Arial';
    //     for (let i = 0; i < labels.length; i++) {
    //       context.fillText(labels[i], 30 + i * stepX - 15, height - 10);
    //     }
    //   }, []);

    const showGridLines = () => {
        const canvas = canvasRef.current;
        const width = canvas.width;
        const height = canvas.height;

        const data = [1000, 19, 3, 5, 2];
        
        const context = canvasRef.current.getContext("2d");


        const stepY2 = (height - 50) / 10;
        for(let i = 1; i < 10; i++ ) {
            context.beginPath();
            context.moveTo(30, i * stepY2);
            context.lineTo(width, i * stepY2);
            context.strokeStyle = '#ddd';
            context.stroke();
        }

        const stepX = (width - 30) / 5;
        for(let i = 1; i < 5; i++ ) {
            context.beginPath();
            context.moveTo(i * 100, 0);
            context.lineTo(i * 100, height - 50);
            context.strokeStyle = '#ddd';
            context.stroke();
        }

    }

    useEffect(() => {

        const data = [1000, 19, 550, 459, 2];
        const labels = ['January', 'February', 'March', 'April', 'May'];

        const canvas = canvasRef.current;
        
        const context = canvasRef.current.getContext("2d");
        
        const width = canvas.width;
        const height = canvas.height;

        const stepY = (height - 50) / Math.max(...data);
        console.log(stepY);
        
        context.beginPath();
        context.moveTo(30, 0);
        context.lineTo(30, height - 50);
        context.lineTo(width, height - 50 );
        context.stroke();

        context.beginPath();
        context.fillStyle = 'black';
        context.font = '12px Arial';
        data.forEach((dataVal, index) => {
            if (index === 0) {
                context.moveTo(30, height -50 - data[0] * stepY);
                context.fillText(labels[index], 30 + 100 * (index) - 10, height - 10);
            } else {
                context.lineTo(100 * (index) , height - 50 - dataVal * stepY);
                context.fillText(labels[index], 100 * (index) - 10, height - 10);
            }
             console.log(100 * index + 1 , height - 50 - dataVal * stepY)
        })
        context.strokeStyle = '#ddd';
        context.stroke();

        // context.fillStyle = 'blue';
        // for (let i = 0; i < data.length; i++) {
        //   context.beginPath();
        //   context.arc(30 + i * stepX, height - data[i] * stepY, 5, 0, Math.PI * 2);
        //   context.fill();
        // }

        context.fillStyle = 'blue';
        data.forEach((dataVal, index) => {
            let i = index === 0 ? 30 : 100 * index;
            context.beginPath();
            context.arc(i, height - 50 - dataVal * stepY, 5, 0, Math.PI * 2);
            context.fill();
        })

        // const stepX = (width - 30) / 10;
        // context.fillStyle = 'black';
        // context.font = '12px Arial';
        // for (let i = 0; i < labels.length; i++) {
        //   context.fillText(labels[i], 30 + i * stepX, height - 10);
        // }
        function fetchInterceptor() {
            const realFetch = window.fetch;
            window.fetch = function(url, options) {
              return realFetch(url, options)
                .then(response => {
                  // Check response status and handle accordingly
                  if (response.redirected) {
                    console.log("print response", response);
                  }
                  return response;
                })
                .catch(error => {
                  console.error('Fetch error:', error);
                  throw error;
                });
            };
          }
          
          // Call the interceptor function to start monitoring fetch requests
        // fetchInterceptor();

        fetch('http://localhost/api/public/redirect').then((response) => console.log("response 2", response.redirected));

    }, [])

    return(
        <Box sx={{padding: "50px"}}>
            <Button onClick={() => showGridLines()}>Show Grid Lines</Button>
             <canvas ref={canvasRef} width={500} height={300}></canvas>
        </Box>
    )
}

export default GraphPage;