const url = "/api/posts/";

const MONTHS = ["JAN","FEB","MAR","APR",
                "MAY","JUN","JUL","AUG",
                "SEP","OCT","NOV","DEC"];

const value = new Array();
const chart = new Array();
const xVal = [];
const yVal = [];

async function getData() {
    const response = await fetch(url).then(res => res.json())
        .then(data => {
            for(let i = 0; i < data.length; i++) {
                value.push([data[i].duration, MONTHS[parseInt(data[i].date.substring(5, 7)) - 1]]);
            }
        });
}
console.log(value);

function sortValues() {
    let map = ((m, a) => (value.forEach(temp => {
        let [num, str] = temp;
        m.set(str, (m.get(str) || 0) + num);
    }), m))
(new Map(), value);

const result = ([...map.entries()].map(([a, b]) => [b, a]));
    for(let i = 0; i < result.length; i++) {
        xVal.push(MONTHS.indexOf(result[i][1])+1);
        yVal.push(result[i][0]);
    }
}


async function createChart() {
    await getData();
    await sortValues();
    const ctx = document.getElementById('workoutChart').getContext('2d')
    const workoutChart = new Chart(ctx, {
        type:'bar',
                data: {
                labels: xVal,
                datasets: [{
                        label: 'Workout hours per Month',
                        data: yVal
                         }]
                }
    })
}

createChart();