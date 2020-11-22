import React from 'react';
import './textarea.css';

export default class Textarea extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <textarea
                className="com-textarea"
                style={{
                    width: this.props.width, height: this.props.height
                }}
                value={this.props.value}
                placeholder={this.props.placeholder}
                onChange={this.props.onChange}
            >

            </textarea>
        )
    }
}