class Button extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            inProp: false,
        }
    }

    render() {
        const props = this.props;

        if (props.isOpen !== undefined && !props.isOpen) {
            return (
                <button className="do-not-use" disabled="disabled">
                    {this.props.value}
                </button>
            )
        }
        return (
            <button
                style={{ width: props.width, height: props.height }}
                className="my-button"
                onPointerOver={() => { this.setState({ inProp: true }) }}
                onPointerOut={() => { this.setState({ inProp: false }) }}
                onClick={this.props.onClick}>
                {this.props.value}
            </button>
        )
    }
}
