class Input extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <input
                className="com-public-input"
                style={{width: this.props.width, height: this.props.height}}
                placeholder={this.props.placeholder}
                type={this.props.type}
                value={this.props.value}
                onChange={this.props.onChange}
                onKeyDown={this.props.onKeyDown}
            />
        )
    }
}
