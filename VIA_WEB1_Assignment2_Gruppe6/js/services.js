

let data = "./project_data.json";

readFile(data)

function readFile(file) {
    const reader = new FileReader();
    const result = reader.target.result
    console.log(result[0]);
}
