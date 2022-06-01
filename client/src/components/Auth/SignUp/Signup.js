import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import { createTheme } from '@mui/material/styles';
import { messageActions } from "../../../store/message-slice";
import { authActions, signup } from "../../../store/auth-slice";
import * as yup from 'yup';
import { useFormik } from "formik";
import { Alert } from "@mui/material";

const passwordRegEx = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#%&])(?=.{8,})/

const validationSchema = yup.object().shape({
    username: yup.string()
        .required('Please Enter the Username')
        .min(2, 'username too short')
        .max(40, 'username too long'),
    email: yup.string()
        .email('Invalid Email')
        .required('Please enter your Email'),
    password: yup.string()
        .required('Please enter your Password')
        .matches(
            passwordRegEx,
            "Must Contain 8 Characters, One Uppercase, One Lowercase, One Number and One Special Case Character"
        ),
});

const Signup = (props) => {
    const [successful, setSuccessful] = useState(false);
    const { message } = useSelector((state) => state.message);
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(messageActions.clearMessage());
    }, [dispatch]);

    const formik = useFormik({
      initialValues: {
        username: '',
        email: '',
        password: ''
      },
      validationSchema: validationSchema,
      onSubmit: (values) => {
        handleSignupSubmit(values);
      }
    })

    const handleSignupSubmit = (values) => {
        setSuccessful(false);
        const signUpRequest = {
            username: values.username,
            email: values.email,
            password: values.password
        };

        dispatch(signup(signUpRequest))
            .unwrap()
            .then(() => {
                setSuccessful(true);
            })
            .catch(() => {
                setSuccessful(false);
            });
        
      props.history.push("/");
    };

  return (
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: 'url(https://source.unsplash.com/random)',
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
              <LockOutlinedIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
              Sign up
            </Typography>
            <Box component="form" noValidate onSubmit={formik.handleSubmit} sx={{ mt: 1 }}>
            <TextField
                margin="normal"
                fullWidth
                id="username"
                label="Username"
                name="username"
                autoFocus
                value={formik.values.username}
                onChange={formik.handleChange}
                error={formik.touched.username && Boolean(formik.errors.username)}
                helperText={formik.touched.username && formik.errors.username}
              />
              <TextField
                margin="normal"
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
                value={formik.values.email}
                onChange={formik.handleChange}
                error={formik.touched.email && Boolean(formik.errors.email)}
                helperText={formik.touched.email && formik.errors.email}
              />
              <TextField
                margin="normal"
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
                value={formik.values.password}
                onChange={formik.handleChange}
                error={formik.touched.password && Boolean(formik.errors.password)}
                helperText={formik.touched.password && formik.errors.password}
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Sign Up
              </Button>
              <Grid container>
                <Grid item>
                  <Link href="http://localhost:3000/" variant="body2">
                    {"Already have an account ? Login"}
                  </Link>
                </Grid>
              </Grid>

              {message && 
                  <Alert severity="error">
                    <strong>{message}</strong>
                  </Alert>
              }
            </Box>
          </Box>
        </Grid>
      </Grid>
  );
}

export default Signup;