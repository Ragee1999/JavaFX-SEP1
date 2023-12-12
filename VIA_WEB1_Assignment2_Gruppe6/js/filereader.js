fetch('../../project_data.json')
    .then(response => response.json())
    .then(allProjects => {

        // Filter projects based on completion status
        const ongoingProjects = allProjects.filter(project => project.completed === "False");
        const completedProjects = allProjects.filter(project => project.completed === "True");

        // Containers for ongoing and completed projects
        const ongoingProjectsContainer = document.querySelector('.ongoing-projects-container');
        const completedProjectsContainer = document.querySelector('.completed-projects-container');
        const tableBody = document.querySelector('.table tbody');

        // Function to determine the image path based on project type
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

        // Function to create project div HTML
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

        // Function to create table row HTML
        const createTableRowHTML = (project, index) => `
            <tr>
                <td>${project.projectName}</td>
                <td>${project.projectType}</td>
                <td>${project.hoursSpent}</td>
                <td>${project.price.toLocaleString('en-US', { style: 'currency', currency: 'USD' })}</td>
                <td>${project.timeline}</td>
                </tr>
        `;

        if (ongoingProjectsContainer) {
            ongoingProjectsContainer.innerHTML = '';

            ongoingProjects.slice(0, 3).forEach((project, index) => {
                const imagePath = getImagePath(project.projectType, index);
                ongoingProjectsContainer.innerHTML += createProjectDivHTML(project, imagePath);
            });

            ongoingProjects.slice(3).forEach((project, index) => {
                tableBody.innerHTML += createTableRowHTML(project, index + 3);
            });
        }

        if (completedProjectsContainer) {
            completedProjectsContainer.innerHTML = '';

            completedProjects.slice(0, 3).forEach((project, index) => {
                const imagePath = getImagePath(project.projectType, index);
                completedProjectsContainer.innerHTML += createProjectDivHTML(project, imagePath);
            });

            completedProjects.slice(3).forEach((project, index) => {
                tableBody.innerHTML += createTableRowHTML(project, index + 3);
            });
        }
    })
    // Eror handling
    .catch(error => {
        // Log the error to the console
        console.error('Error:', error);

        // Select an element on the page to display the error message
        const errorContainer = document.querySelector('.error-container');

        // Check if the error container exists
        if (errorContainer) {
            // Create an error message
            const errorMessage = `En fejl er opstået: ${error.message}`;

            // Update the error container with the error message
            errorContainer.textContent = errorMessage;

            errorContainer.style.display = 'block';
            errorContainer.style.color = 'red';
        }
    });