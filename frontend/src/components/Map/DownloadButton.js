import React, { useState } from 'react';


const fs = require('fs')

const DownloadButton = (props) => {
    console.log(props.planInfo.id)

    const exportToJson = () => {
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
    }
    return(
        <div className='DownloadButton'>
            <button className='btn btn-primary' onClick={exportToJson} > Download </button>
        </div>
    )
}

export default DownloadButton;