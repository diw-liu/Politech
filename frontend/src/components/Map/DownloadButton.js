import React, { useState } from 'react';


const fs = require('fs')

const DownloadButton = (props) => {

    const exportToJson = async () => {
      const data = await fetch("/job/redistricted")
                              .then(data => data.json())
      const filename = props.measure.id+'.json';

      const contentType = "application/json;charset=utf-8;";
      const objectData = JSON.stringify(data);
      
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
    }
    return(
        <div className='DownloadButton'>
            <button className='btn btn-info' onClick={exportToJson} > Download </button>
        </div>
    )
}

export default DownloadButton;