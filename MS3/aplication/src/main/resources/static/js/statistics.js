fetch("/api/posts/")
    .then(res => {
        res.json()
            .then(
                data => {
                    console.log(data);
                    if (data.length > 0) {
                        var temp = "";
                        data.forEach((post) => {
                            temp += "<tr>";
                            temp += "<td>" + post.id + "</td>";
                            temp += "<td>" + post.title + "</td>";
                            temp += "<td>" + post.date + "</td>";
                            temp += "<td>" + post.imageLink + "</td>";
                            temp += "</tr>";
                        });
                        document.getElementById('posts').innerHTML = temp;
                    }
                }
            )
    })