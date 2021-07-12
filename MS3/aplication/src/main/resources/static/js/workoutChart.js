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

function sortValues() {
    let map = ((m, a) => (value.forEach(temp => {
        let [num, str] = temp;
        m.set(str, (m.get(str) || 0) + num);
    }), m))
    (new Map(), value);

    const result = ([...map.entries()].map(([a, b]) => [b, a]));
    for(let i = 0; i < result.length; i++) {
        xVal.push(result[i][1]);
        yVal.push(result[i][0]);
    }
    xVal.sort(function(a,b) {
        return MONTHS.indexOf(a) > MONTHS.indexOf(b);
    });
}


async function createChart() {
    await getData();
    await sortValues();
    const ctx = document.getElementById('workoutChart').getContext('2d')
    Chart.defaults.font.size = 16;
    Chart.defaults.borderColor = 'rgba(148, 220, 255, 0.3)';
    Chart.defaults.color = 'rgba(237, 249, 255, 0.8)';
    const workoutChart = new Chart(ctx, {
        type:'bar',
        data: {
            labels: xVal,
            datasets: [{
                label: 'Workout hours per Month',
                data: yVal,
                backgroundColor: [
                    'rgba(3, 190, 252, 0.8)'
                ],
                borderColor: [
                    'rgba(14, 41, 54, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            plugins: {
                legend: {
                    labels: {
                        color: 'rgba(237, 249, 255, 0.8)'
                    }
                }
            },
    }
    })
}

createChart();