var id = document.getElementById('workoutChart');

const MONTHS = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December'
];

const inputs = {
    min: 0,
    max: 200,
    decimals: 2,
    continuity: 1
};

const json = fetch('/api/posts/')
                .then(res => res.json())
                .then((out) => {
                    return out;
                });

const countDuration = () => {
    for(var i = 0; i < json.length; i++) {
        console.log(json.duration)
    }
}

const generateLabels = () => {
    return MONTHS;
}

const data = {
    labels: generateLabels(),
    datasets: [{
            label: 'Workout hours per month',
            data: countDuration()
            }
    ]};

let yValues = data;

var workoutChart = new Chart(id, {
    type: 'line',
    data: {
        labels: generateLabels(),
        datasets: [{
            backgorundColor: "rgba(255,255,255,1)",
            borderColor: "rgba(255,255,255,0.1)",
            data: yValues,
            fill: true,
            smooth: true
        }]
    }
});
