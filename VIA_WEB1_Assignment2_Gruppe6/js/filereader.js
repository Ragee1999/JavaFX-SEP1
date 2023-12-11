fetch('../project_data.json')
    .then(response => response.json())
    .then(allProjects => {
        const ongoingProjects = allProjects.filter(project => project.completed === "False");

        const projectsContainer = document.querySelector('.projects-container');
        const tableBody = document.querySelector('.table tbody');

        const getImagePath = (projectType, index) => {
            switch (projectType) {
                case 'Industrial':
                    return `Images/industrial${index + 1}cropped.png`;
                case 'Road Construction':
                    return `Images/road${index + 1}cropped.png`;
                case 'Residential':
                    return `Images/residential${index + 1}cropped.png`;
                case 'Commercial':
                    return `Images/commercial${index + 1}cropped.png`;
                default:
                    return 'Images/default-image.png';
            }
        };

        projectsContainer.innerHTML = '';
        tableBody.innerHTML = '';

        const createProjectDivHTML = (project, imagePath) => `
            <div class="individual-project">
                <img class="project-img" src="${imagePath}" alt="Project Image" />
                <div class="textbox">
                    <div>
                        <div>Project name: ${project.projectName}</div>
                        <div>Project type: ${project.projectType}</div>
                        <div>Manhours used: ${project.hoursSpent}</div>
                        <div>Est. price: ${project.price.toLocaleString('en-US', { style: 'currency', currency: 'USD' })}</div>
                        <div>Est. timeline (months): ${project.timeline}</div>
                    </div>
                </div>
            </div>
        `;

        const createTableRowHTML = (project, index) => `
            <tr>
                <td>${project.projectName}</td>
                <td>${project.projectType}</td>
                <td>${project.hoursSpent}</td>
                <td>${project.price.toLocaleString('en-US', { style: 'currency', currency: 'USD' })}</td>
                <td>${project.timeline}</td>
                </tr>
        `;

        ongoingProjects.slice(0, 3).forEach((project, index) => {
            const imagePath = getImagePath(project.projectType, index);
            projectsContainer.innerHTML += createProjectDivHTML(project, imagePath);
        });

        ongoingProjects.slice(3).forEach((project, index) => {
            tableBody.innerHTML += createTableRowHTML(project, index + 3);
        });
    })
    .catch(error => console.error('Error:', error));