import React, {Component} from 'react';
import Backlog from "./Backlog";

class ProjectBoard extends Component {
    render() {
        return (
            <div className="container">
                <a href=""
                   className="btn btn-primary mb-3">
                    <i className="fas fa-plus-circle">Create Project Task</i>
                </a>
                <br />
                <hr />
                <Backlog />
            </div>
        );
    }
}

export default ProjectBoard;