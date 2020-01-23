import React from "react";
import { Link } from "react-router-dom";

const CreateProjectButton = () => {
    return (
        <React.Fragment>
            <Link className="btn btn-lg btn-info"
                to="/addProject" >Create a Project</Link>
        </React.Fragment>
    )
};

export default CreateProjectButton;