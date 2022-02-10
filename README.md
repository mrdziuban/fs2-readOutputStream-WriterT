# fs2-readOutputStream-WriterT

This is a repository to demonstrate an issue using [`fs2.io.readOutputStream`](https://github.com/typelevel/fs2/blob/v3.2.4/io/jvm/src/main/scala/fs2/io/ioplatform.scala#L73) and [`cats.data.WriterT`](https://github.com/typelevel/cats/blob/v2.7.0/core/src/main/scala/cats/data/WriterT.scala#L7).

Clone this repository, `cd` into the directory, and run one of the following commands to reproduce the issue on either fs2 v2.x or v3.x:

```bash
sbt fs2-2/run
sbt fs2-3/run
```

The output of both is:

```
[info] running (fork) example.Test
[info] (List(string 2),())
```

In both cases, I would expect the final result to include both `"string 1"` and `"string 2"` given the monoidal
accumulation behavior of `WriterT`, but `"string 1"` is missing.
