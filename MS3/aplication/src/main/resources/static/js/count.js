fetch("/api/posts/")
    .then(res => {
        res.json()
            .then(
                data => {
                    console.log(data.length);
                    document.getElementById('number').innerHTML = data.length;
                    });
                })
