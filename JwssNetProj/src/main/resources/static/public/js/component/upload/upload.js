import React, { Fragment } from 'react';

function Upload(props) {
    return (
        <Fragment>
            <input type="file"
                width={props.width}
                height={props.height}
                accept={props.accept}
                onChange={props.onChange}
                onClick={props.onClick}
            />
            <span>
                {
                    props.pre === '' ? '' :
                        (
                            <Fragment>
                                封面预览: 
                                <img style={{ width: 200, height: 120, border: '1px solid #FC7FAD' }}
                                    src={URL.createObjectURL(props.pre)} alt="" />
                            </Fragment>
                        )
                }
            </span>

        </Fragment>
    )
}

export default Upload;