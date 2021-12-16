import React, { useState } from 'react';


const fs = require('fs')

const DownloadButton = (props) => {
    console.log(props.planInfo.id)

    const exportToJson = () => {
<<<<<<< HEAD
        const filename = props.planInfo.id+'.json';
        const contentType = "application/json;charset=utf-8;";
        const objectData = JSON.stringify(props.planInfo);
        if (window.navigator && window.navigator.msSaveOrOpenBlob) {
          var blob = new Blob([decodeURIComponent(encodeURI(objectData))], { type: contentType });
          navigator.msSaveOrOpenBlob(blob, filename);
        } else {
          var a = document.createElement('a');
          a.download = filename;
          a.href = 'data:' + contentType + ',' + encodeURIComponent(objectData);
          a.target = '_blank';
          document.body.appendChild(a);
          a.click();
          document.body.removeChild(a);
        }
=======
      const filename = props.planInfo.id+'.json';
      const contentType = "application/json;charset=utf-8;";
      const objectData = JSON.stringify(props.planInfo);
      if (window.navigator && window.navigator.msSaveOrOpenBlob) {
        var blob = new Blob([decodeURIComponent(encodeURI(objectData))], { type: contentType });
        navigator.msSaveOrOpenBlob(blob, filename);
      } else {
        var a = document.createElement('a');
        a.download = filename;
        a.href = 'data:' + contentType + ',' + encodeURIComponent(objectData);
        a.target = '_blank';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
>>>>>>> 0cc026181a3ca71609228594d3417a9d8b467327
      }
    }
    return(
        <div className='DownloadButton'>
            <button className='btn btn-info' onClick={exportToJson} > Download </button>
        </div>
    )
}

export default DownloadButton;