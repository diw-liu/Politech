import React, { useState, useEffect } from 'react';
import '../css/InfoMenu.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';

export default function InfoMenu() {
  return (
    <div className='InfoMenu'>
      <div class='container'>
        <p>Maryland</p>
        <ul class='nav nav-pills' role='tablist'>
          <li class='nav-item' role='presentation'>
            <button class='nav-link' data-bs-toggle='tab' data-bs-target='#enacted' role='tab' type='button' aria-selected='true'> Enacted </button>
          </li>
          <li class='nav-item' role='presentation'>
            <button class='nav-link' data-bs-toggle='tab' data-bs-target='#generated' role='tab' type='button' aria-selected='false'> Generated </button>
          </li>
        </ul>
        <div class="tab-content" id="pills-tabContent">
          <div class="tab-pane fade show active" id='enacted' role="tabpanel" aria-labelledby='enacted-tab'>
            <table class="table table-sm">
              <thead>
                <tr>
                  <th scope="col">District #</th>
                  <th scope="col">Population</th>
                  <th scope="col">Minority %</th>
                  <th scope="col">Dev</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th scope="row">1</th>
                  <td>1</td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th scope="row">2</th>
                  <td>2</td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th scope="row">3</th>
                  <td>3</td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th scope="row">4</th>
                  <td>4</td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th scope="row">5</th>
                  <td>5</td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th scope="row">6</th>
                  <td>6</td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th scope="row">7</th>
                  <td>7</td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th scope="row">8</th>
                  <td>8</td>
                  <td></td>
                  <td></td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="tab-pane fade" id='generated' role="tabpanel" aria-labelledby='generated-tab'>
            <table class="table table-sm">
              <thead>
                <tr>
                  <th scope="col">District #</th>
                  <th scope="col">Population</th>
                  <th scope="col">Minority %</th>
                  <th scope="col">Dev</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th scope="row">1</th>
                  <td>1</td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th scope="row">2</th>
                  <td>2</td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th scope="row">3</th>
                  <td>3</td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th scope="row">4</th>
                  <td>4</td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th scope="row">5</th>
                  <td>5</td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th scope="row">6</th>
                  <td>6</td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th scope="row">7</th>
                  <td>7</td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th scope="row">8</th>
                  <td>8</td>
                  <td></td>
                  <td></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}