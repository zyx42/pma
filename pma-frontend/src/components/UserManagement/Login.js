import React, {Component} from 'react';
import PropTypes from "prop-types";
import { connect } from "react-redux";
import classnames from "classnames";
import { login } from "../../actions/securityActions";

class Login extends Component {
    constructor() {
        super();
        this.state = {
            username: "",
            password: ""
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit(e) {
        e.preventDefault();
        const LoginRequest = {
            username: this.state.username,
            password: this.state.password
        };

        this.props.login(LoginRequest);
    }

    onChange(e) {
        this.setState({
            [e.target.name] : e.target.value
        });
    }

    render() {
        return (
            <div className="login">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h1 className="display-4 text-center">Log In</h1>
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input type="email"
                                           className="form-control form-control-lg"
                                           placeholder="Email Address"
                                           value={this.state.username}
                                           onChange={this.onChange}
                                           name="username" />
                                </div>
                                <div className="form-group">
                                    <input type="password"
                                           className="form-control form-control-lg"
                                           placeholder="Password"
                                           value={this.state.password}
                                           onChange={this.onChange}
                                           name="password" />
                                </div>
                                <input type="submit"
                                       className="btn btn-info btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

Login.propTypes = {
    login: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    security: state.security,
    errors: state.errors
});

export default connect(
    mapStateToProps,
    {login}
)(Login);